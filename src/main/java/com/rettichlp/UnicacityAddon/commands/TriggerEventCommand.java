package com.rettichlp.UnicacityAddon.commands;

import com.rettichlp.UnicacityAddon.base.abstraction.AbstractionLayer;
import com.rettichlp.UnicacityAddon.base.abstraction.UPlayer;
import com.rettichlp.UnicacityAddon.base.text.ColorCode;
import com.rettichlp.UnicacityAddon.base.text.Message;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author RettichLP
 */
public class TriggerEventCommand extends CommandBase {

    @Override @Nonnull public String getName() {
        return "triggerevent";
    }

    @Override @Nonnull public String getUsage(@Nonnull ICommandSender sender) {
        return "/triggerevent [TriggerableEvent]";
    }

    @Override @Nonnull public List<String> getAliases() {
        return Collections.singletonList("tevent");
    }

    @Override public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, String[] args) {
        UPlayer p = AbstractionLayer.getPlayer();

        if (args.length == 1) {
            TriggerableEvent triggerableEvent = TriggerableEvent.valueOf(args[0]);
            p.sendMessageAsString(triggerableEvent.getTriggerMesage());
        } else Message.getBuilder()
                .error()
                .space()
                .of("Syntax: " + getUsage(sender)).color(ColorCode.GRAY).advance()
                .sendTo(p.getPlayer());
    }

    @Override @Nonnull public List<String> getTabCompletions(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, Arrays.asList(TriggerableEvent.values()));
        } else {
            return Collections.emptyList();
        }
    }

    private enum TriggerableEvent {
        BOMB_PLACED("News: ACHTUNG! Es wurde eine Bombe in der N??he von NAVIPUNKT gefunden!"),
        BOMB_REMOVED("News: Die Bombe konnte erfolgreich entsch??rft werden!"),
        CAR_OPEN("[Car] Du hast deinen AUTONAME aufgeschlossen."),
        CAR_CLOSED("[Car] Du hast deinen AUTONAME abgeschlossen.");

        private final String triggerMesage;

        TriggerableEvent(String triggerMesage) {
            this.triggerMesage = triggerMesage;
        }

        public String getTriggerMesage() {
            return triggerMesage;
        }
    }
}