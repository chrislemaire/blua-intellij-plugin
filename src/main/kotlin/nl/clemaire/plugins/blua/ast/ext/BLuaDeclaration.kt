package nl.clemaire.plugins.blua.ast.ext

import com.github.izhangzhihao.rainbow.brackets.util.findPrevSibling
import com.intellij.psi.PsiElement

interface BLuaDeclaration : PsiElement {
    fun scope(): Map<String, PsiElement>
}
