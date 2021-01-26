package nl.clemaire.plugins.blua.ast.ext

import com.intellij.psi.PsiElement

interface BLuaIdsExt : PsiElement {
    @JvmDefault
    fun getIds(): List<PsiElement> = children.filter { it.text != "," }
}
