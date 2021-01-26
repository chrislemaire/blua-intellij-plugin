package nl.clemaire.plugins.blua.ast.ext

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import nl.clemaire.plugins.blua.ast.*

interface BLuaNamedFunctionStmtExt : PsiNameIdentifierOwner, Scoped {
    /**
     * Gets the BLuaId element under this Function node.
     */
    fun getIdToken(): PsiElement?

    /**
     * Gets the BLuaParams element under this Function node.
     */
    fun getParams(): BLuaParams?

    @JvmDefault
    override fun getNameIdentifier(): PsiElement? = getIdToken()

    @JvmDefault
    override fun getName(): String? = nameIdentifier?.text

    @JvmDefault
    override fun setName(name: String): PsiElement {
        node.replaceChild(getIdToken()?.node!!, BLuaElementFactory.createName(project, name).node)
        return this
    }

    @JvmDefault
    override fun scope(): Map<String, PsiElement> {
        val declared = (getIdToken()?.let { mapOf(it.text to it) } ?: mapOf()) +
                (getParams()?.scope() ?: mapOf())

        // Declared parameters and function name override previous scope names
        return getPreviousScope() + declared
    }
}
