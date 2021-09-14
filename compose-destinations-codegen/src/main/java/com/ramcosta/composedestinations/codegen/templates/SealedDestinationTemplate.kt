package com.ramcosta.composedestinations.codegen.templates

import com.ramcosta.composedestinations.codegen.commons.CORE_DESTINATION_SPEC
import com.ramcosta.composedestinations.codegen.commons.CORE_NAV_GRAPH_SPEC
import com.ramcosta.composedestinations.codegen.commons.GENERATED_DESTINATION
import com.ramcosta.composedestinations.codegen.commons.GENERATED_NAV_GRAPH

val sealedDestinationTemplate = """
package com.ramcosta.composedestinations

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

/**
 * When using the code gen module, all APIs will expose
 * $GENERATED_DESTINATION which is a sealed version of [$CORE_DESTINATION_SPEC]
 */
sealed interface $GENERATED_DESTINATION : $CORE_DESTINATION_SPEC

data class $GENERATED_NAV_GRAPH(
    override val name: String,
    override val startDestination: $GENERATED_DESTINATION,
    override val destinations: Map<String, $GENERATED_DESTINATION>,
    override val nestedNavGraphs: List<$GENERATED_NAV_GRAPH> = emptyList()
): $CORE_NAV_GRAPH_SPEC

fun NavHostController.navigateToGraph(
    navGraph: $GENERATED_NAV_GRAPH,
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(navGraph.name, navOptionsBuilder)
}

""".trimIndent()