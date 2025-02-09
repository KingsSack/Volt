package dev.kingssack.volt.opmode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta

private const val GROUP = "Volt"

fun metaForClass(cls: Class<out OpMode?>): OpModeMeta {
    return OpModeMeta.Builder()
        .setName("RoadRunner - ${cls.simpleName}")
        .setGroup(GROUP)
        .setFlavor(OpModeMeta.Flavor.TELEOP)
        .build()
}

class CustomOpMode {
    companion object {
        @JvmStatic
        @OpModeRegistrar
        fun register(manager: OpModeManager) {
            // manager.register(metaForClass())
        }
    }
}