package nl.clemaire.plugins.blua.ast

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import nl.clemaire.plugins.blua.BLuaFileType
import nl.clemaire.plugins.blua.BLuaLanguage

class BLuaFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, BLuaLanguage) {
    override fun getFileType(): FileType = BLuaFileType

    override fun toString(): String = "BLua File"
}
