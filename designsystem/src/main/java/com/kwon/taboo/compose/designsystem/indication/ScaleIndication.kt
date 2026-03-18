package com.kwon.taboo.compose.designsystem.indication

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.ui.node.DelegatableNode

object ScaleIndication: IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return ScaleNode(
            interactionSource
        )
    }

    override fun equals(other: Any?): Boolean {
        return other === ScaleIndication
    }

    override fun hashCode(): Int {
        return 100
    }
}