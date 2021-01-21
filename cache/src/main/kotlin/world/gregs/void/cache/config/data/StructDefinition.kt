package world.gregs.void.cache.config.data

import world.gregs.void.cache.Definition
import world.gregs.void.cache.definition.Parameterized

/**
 * @author GregHib <greg@gregs.world>
 * @since April 07, 2020
 */
data class StructDefinition(
    override var id: Int = -1,
    override var params: HashMap<Long, Any>? = null
) : Definition, Parameterized