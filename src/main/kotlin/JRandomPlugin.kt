package top.jie65535.jrandom

import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.utils.info
import java.awt.image.*
import javax.imageio.ImageIO

object JRandomPlugin : KotlinPlugin(
    JvmPluginDescription(
        id = "top.jie65535.mirai-console-jrandom-plugin",
        name = "随意插件",
        version = "0.1.0"
    ) {
        author("jie65535")
        info("随意插件")
    }
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }

        GlobalEventChannel
            .parentScope(this)
            .subscribeMessages {
                "test" reply {

                    "OK"
                }
            }

        object : SimpleCommand(this, "test", description = "test") {
            @Handler
            suspend fun CommandSender.onCommand() {
                val bi = BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB)
                val ig2 = bi.createGraphics()
                ig2.drawString("Hello world", 10, 100)
                ImageIO.write(bi, "PNG", dataFolderPath.resolve("test.PNG").toFile())
            }
        }.register()
    }
}
