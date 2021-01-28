package nl.clemaire.plugins.blua.ast.mixin.misc

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaParams

abstract class BLuaParamsMixin(node: ASTNode) : ASTWrapperPsiElement(node), BLuaParams {
    override fun scope(): Map<String, PsiElement> =
        paramList.map { it.idToken.text to it.idToken }.toMap()
}
