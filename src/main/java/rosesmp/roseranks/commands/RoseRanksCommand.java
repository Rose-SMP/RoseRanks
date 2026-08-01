package rosesmp.roseranks.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentTypeString;
import com.mojang.brigadier.builder.ArgumentBuilderLiteral;
import com.mojang.brigadier.builder.ArgumentBuilderRequired;
import net.minecraft.core.net.command.CommandManager;
import net.minecraft.core.net.command.CommandSource;

import static rosesmp.roseranks.RoseRanks.groups;

@SuppressWarnings("ALL")
public class RoseRanksCommand implements CommandManager.CommandRegistry {

	@Override public void register(CommandDispatcher<CommandSource> commandDispatcher) {
		String[] literals = {"roseranks", "rr"};
		for (String literal : literals) {
			commandDispatcher.register((ArgumentBuilderLiteral) ArgumentBuilderLiteral
				.literal(literal)
				.requires(source -> ((CommandSource) source).hasAdmin())
				.then(ArgumentBuilderRequired
					.argument("subcommand", ArgumentTypeString.word())
					.executes(context -> {
						String subcommand = context.getArgument("subcommand", String.class);
						CommandSource sender = (CommandSource) context.getSource();

						switch (subcommand) {
							case "groups", "gr" -> groupsCommand(sender);
							default -> sender.sendMessage("Invalid subcommand!");
						}
						return 1;
					})));
		}
	}

	/**
	 * Prints a list of the server's exisitng groups.
	 * @param sender Can be console or a player.
	 */
	private void groupsCommand(CommandSource sender) {
		sender.sendMessage("Server's groups:");

		for (String groupName : groups().keySet()) {
			sender.sendMessage(groupName);
		}
	}
}
