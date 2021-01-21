package world.gregs.void.cache.config.data

import world.gregs.void.cache.Definition

/**
 * @author GregHib <greg@gregs.world>
 * @since April 07, 2020
 */
data class MapSceneDefinition(
    override var id: Int = -1,
    var sprite: Int = 0,
    var colour: Int = 0,
    var aBoolean1741: Boolean = false
) : Definition