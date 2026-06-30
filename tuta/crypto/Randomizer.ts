import sjcl from "../internal/sjcl.js"
import { CryptoError } from "@tutao/crypto/error"
import { EntropySource } from "../CryptoTypes"
import { EntropyDataChunk } from "./EntropyDataChunk"

export class Randomizer 
{
	random: any
	constructor() {
		this.random = new sjcl.prng(6)
	}

	addEntropy(entropyCache: Array<EntropyDataChunk>): Promise<void> {
		for (const entry of entropyCache) 
    {
			this.random.addEntropy(entry.data, entry.entropy, entry.source)
		}
		return Promise.resolve()
	}

	addStaticEntropy(bytes: Uint8Array) {
		for (const byte of bytes) {
			this.random.addEntropy(byte, 8, "static")
		}
	}

	isReady(): boolean {
		return this.random.isReady() !== 0
	}

	generateRandomData(nbrOfBytes: number): Uint8Array {
		try {

			let nbrOfWords = Math.floor((nbrOfBytes + 3) / 4)
			let words = this.random.randomWords(nbrOfWords)
			let arrayBuffer = sjcl.codec.arrayBuffer.fromBits(words, false)

			return new Uint8Array(arrayBuffer, 0, nbrOfBytes) // truncate the arraybuffer as precaution
		} 
    catch (e) 
    {
			throw new CryptoError("error during random number generation", e as Error)
		}
	}

	generateRandomNumber(nbrOfBytes: number): number {
		const bytes = this.generateRandomData(nbrOfBytes)
		let result = 0

		for (let i = 0; i < bytes.length; ++i) 
    {
			result += bytes[i] << (i * 8)
		}
		return result
	}
}
export const random: Randomizer = new Randomizer()
