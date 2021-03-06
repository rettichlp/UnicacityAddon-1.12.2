package com.rettichlp.UnicacityAddon.commands;

import com.rettichlp.UnicacityAddon.base.abstraction.AbstractionLayer;
import com.rettichlp.UnicacityAddon.base.location.ATM;
import com.rettichlp.UnicacityAddon.base.location.NavigationUtils;
import com.rettichlp.UnicacityAddon.base.text.ColorCode;
import com.rettichlp.UnicacityAddon.base.text.Message;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author RettichLP
 */
public class NearestATMCommand extends CommandBase {

    @Override @Nonnull public String getName() {
        return "nearestatm";
    }

    @Override @Nonnull public String getUsage(@Nonnull ICommandSender sender) {
        return "/nearestatm";
    }

    @Override @Nonnull public List<String> getAliases() {
        return Collections.singletonList("natm");
    }

    @Override public boolean checkPermission(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender) {
        return true;
    }

    @Override public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) {
        Map.Entry<Double, ATM> nearestATM = NavigationUtils.getNearestATM();

        AbstractionLayer.getPlayer().sendMessage(Message.getBuilder()
                .prefix()
                .of("ATM").color(ColorCode.GRAY).advance()
                .space()
                .of(String.valueOf(nearestATM.getValue().getID())).color(ColorCode.AQUA).bold().advance()
                .space()
                .of("ist").color(ColorCode.GRAY).advance()
                .space()
                .of(Math.round(nearestATM.getKey()) + "m").color(ColorCode.AQUA).bold().advance()
                .space()
                .of("entfernt.").color(ColorCode.GRAY).advance()
                .space()
                .of("??? Navi")
                    .color(ColorCode.RED)
                    .clickEvent(ClickEvent.Action.RUN_COMMAND, nearestATM.getValue().getNaviCommand())
                    .hoverEvent(HoverEvent.Action.SHOW_TEXT, Message.getBuilder()
                            .of("Route anzeigen").color(ColorCode.RED).advance()
                            .createComponent())
                    .advance()
                .createComponent());
    }
}