package nl.clemaire.plugins.blua

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object BLuaFileType : LanguageFileType(BLuaLanguage) {
    override fun getName(): String = "Better Lua File"
    override fun getDescription(): String = "Better Lua language file"
    override fun getDefaultExtension(): String = "blua"
    override fun getIcon(): Icon = BLuaIcons.fileIcon
}
