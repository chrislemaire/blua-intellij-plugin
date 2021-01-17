package nl.clemaire.plugins.blua.ast.mixin

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import nl.clemaire.plugins.blua.ast.BLuaParams
import nl.clemaire.plugins.blua.ast.ext.BLuaDeclaration

abstract class BLuaVarRefMixin(node: ASTNode) : ASTWrapperPsiElement(node), BLuaParams, BLuaDeclaration {
}
