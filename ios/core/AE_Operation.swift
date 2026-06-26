import Foundation

public protocol AsynchronousExecution {
    func execute() async
}

open class AsynchronousExecutionOperation: AsynchronousOperation {
    private let execution: AsynchronousExecution
    private var task: Task<Void, Never>?

    public init(execution: AsynchronousExecution) 
    {
        self.execution = execution
    }

    override public func main() 
    {
        guard !isCancelled 
        else {
            return
        }

        task = Task(priority: .low) { [weak self] in
            guard self?.isCancelled == false else { return }
            await self?.execution.execute()
            self?.state = .finished
        }
    }

    override open func cancel() 
    {
        task?.cancel()
        super.cancel()
    }
}

public class AggregatedAsynchronousExecution: AsynchronousExecution {
    private let executions: [AsynchronousExecution]

    public init(executions: [AsynchronousExecution]) {
        self.executions = executions
    }

    public func execute() async {
        await executions.forEach { await $0.execute() }
    }
}
