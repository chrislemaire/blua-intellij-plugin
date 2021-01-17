package nl.clemaire.plugins.blua.ast.ext

import com.intellij.psi.PsiElement
import nl.clemaire.plugins.blua.ast.BLuaBlock
import nl.clemaire.plugins.blua.ast.BLuaId
import nl.clemaire.plugins.blua.ast.BLuaParams
import nl.clemaire.plugins.blua.ast.previousScope

interface BLuaFunctionDeclaration : BLuaDeclaration {
    fun getId(): BLuaId?
    fun getBlock(): BLuaBlock?
    fun getParams(): BLuaParams?

    @JvmDefault
    override fun scope(): Map<String, PsiElement> {
        val declared = (getId()?.let { mapOf(it.text to this) } ?: mapOf()) +
                (getParams()?.scope() ?: mapOf())

        // Declared parameters and function name override previous scope names
        return previousScope() + declared
    }
}
