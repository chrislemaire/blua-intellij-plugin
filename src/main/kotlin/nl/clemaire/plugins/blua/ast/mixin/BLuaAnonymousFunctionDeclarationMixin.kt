package nl.clemaire.plugins.blua.ast.mixin

import com.intellij.lang.ASTNode
import nl.clemaire.plugins.blua.ast.BLuaId
import nl.clemaire.plugins.blua.ast.ext.BLuaFunctionDeclaration
import nl.clemaire.plugins.blua.ast.impl.BLuaExprImpl

abstract class BLuaAnonymousFunctionDeclarationMixin(node: ASTNode) : BLuaExprImpl(node), BLuaFunctionDeclaration {
    override fun getId(): BLuaId? = null
}
