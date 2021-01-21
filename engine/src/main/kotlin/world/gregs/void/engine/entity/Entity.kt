package world.gregs.void.engine.entity

import world.gregs.void.engine.map.Tile

/**
 * An identifiable object with a physical spatial location
 * @author GregHib <greg@gregs.world>
 * @since March 28, 2020
 */
interface Entity {
    val id: Int
    var tile: Tile
}