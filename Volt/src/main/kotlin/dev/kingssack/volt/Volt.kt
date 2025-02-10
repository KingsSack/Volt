package dev.kingssack.volt

import android.content.Context
import com.qualcomm.robotcore.util.WebHandlerManager
import dev.kingssack.volt.web.StaticAssetHandler
import org.firstinspires.ftc.ftccommon.external.WebHandlerRegistrar
import org.firstinspires.ftc.robotcore.internal.system.AppUtil
import java.io.IOException

class Volt {
    companion object {
        @JvmStatic
        @WebHandlerRegistrar
        fun attachWebServer(context: Context?, manager: WebHandlerManager) {
            val activity = AppUtil.getInstance().activity ?: return
            val assetManager = activity.assets

            // Register all static files from the public directory
            try {
                assetManager.list("public")?.forEach { file ->
                    val path = "public/$file"
                    if (assetManager.list(path)?.isNotEmpty() == true) {
                        // Handle subdirectories
                        addAssetHandlers(manager, assetManager, path)
                    } else {
                        // Register individual files
                        manager.register("/volt/$file", StaticAssetHandler(assetManager, path))
                    }
                }
                // Register index.html as the root
                manager.register("/volt", StaticAssetHandler(assetManager, "public/index.html"))
            } catch (e: IOException) {
                // Log error if files can't be accessed
                e.printStackTrace()
            }
        }

        private fun addAssetHandlers(
            manager: WebHandlerManager,
            assetManager: android.content.res.AssetManager,
            path: String
        ) {
            assetManager.list(path)?.forEach { file ->
                val fullPath = "$path/$file"
                val webPath = fullPath.replace("public/", "")
                if (assetManager.list(fullPath)?.isNotEmpty() == true) {
                    addAssetHandlers(manager, assetManager, fullPath)
                } else {
                    manager.register("/volt/$webPath", StaticAssetHandler(assetManager, fullPath))
                }
            }
        }
    }
}