package com.rettichlp.UnicacityAddon.commands;

import com.rettichlp.UnicacityAddon.base.abstraction.AbstractionLayer;
import com.rettichlp.UnicacityAddon.base.location.Job;
import com.rettichlp.UnicacityAddon.base.location.NavigationUtils;
import com.rettichlp.UnicacityAddon.base.text.ColorCode;
import com.rettichlp.UnicacityAddon.base.text.Message;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.event.ClickEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NearestJobCommand extends CommandBase {

    @Override public String getName() {
        return "nearestjob";
    }

    @Override public String getUsage(ICommandSender sender) {
        return "/nearestjob";
    }

    @Override public List<String> getAliases() {
        return Arrays.asList("njob");
    }

    @Override public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        Map.Entry<Double, Job> nearestJob = NavigationUtils.getNearestJob();

        AbstractionLayer.getPlayer().sendMessage(Message.getBuilder()
                .prefix()
                .of("Job").color(ColorCode.GRAY).advance()
                .space()
                .of(nearestJob.getValue().getName()).color(ColorCode.AQUA).bold().advance()
                .space()
                .of("ist").color(ColorCode.GRAY).advance()
                .space()
                .of(Math.round(nearestJob.getKey()) + "m").color(ColorCode.AQUA).bold().advance()
                .space()
                .of("entfernt.").color(ColorCode.GRAY).advance()
                .space()
                .of("➡ Navi").color(ColorCode.RED).clickEvent(ClickEvent.Action.RUN_COMMAND, nearestJob.getValue().getNaviCommand()).advance()
                .createComponent());
    }
}