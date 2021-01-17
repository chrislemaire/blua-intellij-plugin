package nl.clemaire.plugins.blua.ast.mixin

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaParams
import nl.clemaire.plugins.blua.ast.ext.BLuaDeclaration
import nl.clemaire.plugins.blua.ast.previousScope

abstract class BLuaParamsMixin(node: ASTNode) : ASTWrapperPsiElement(node), BLuaParams, BLuaDeclaration {
    override fun scope(): Map<String, PsiElement> {
        val declared = ids?.idList?.map { it.text to it }?.toMap() ?: mapOf()

        // Declared parameters and function name override previous scope names
        return previousScope() + declared
    }
}
