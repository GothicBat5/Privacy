import Foundation

public final class CommandComposite: Command {
    private var commands: [Command] = []

    public init(commands: [Command]) 
    {
        self.commands = commands
    }

    public func add(command: Command) 
    {
        commands.append(command)
    }

    public func remove(command: Command) 
    {
        commands = commands.filter { $0 !== command }
    }

    public func execute() 
    {
        for command in commands {
            command.execute()
        }
    }
}
