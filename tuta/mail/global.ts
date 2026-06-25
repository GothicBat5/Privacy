export { NativeApp } from "../types/globals"

type Tutao = {
	currentView: TopLevelView | null
	m: typeof Mithril
	lang: LanguageViewModel
	client: ClientDetector
	root: RootView
	locator: CommonLocator | null
	nativeApp? // Will either be IosNativeTransport or null
}

declare global {
	interface Window {
		tutao: Tutao
		logger: Logger
		whitelabelCustomizations: WhitelabelCustomizations | undefined
		nativeApp: NativeApp
		nativeAppWebDialog: NativeApp | undefined
	}

	interface WorkerGlobalScope {
		locator: WorkerLocatorType
	}
}
