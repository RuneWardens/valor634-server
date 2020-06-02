package rs.dusk.engine.path

import rs.dusk.engine.model.entity.Direction
import rs.dusk.engine.model.world.Tile
import rs.dusk.engine.model.world.map.collision.CollisionFlag
import rs.dusk.engine.model.world.map.collision.flag

/**
 * @author Greg Hibberd <greg@greghibberd.com>
 * @since May 18, 2020
 */
interface TraversalStrategy {
    fun blocked(x: Int, y: Int, plane: Int, direction: Direction): Boolean

    fun blocked(tile: Tile, direction: Direction): Boolean = blocked(tile.x, tile.y, tile.plane, direction)

    val type: TraversalType
    val extra: Int// Collides with entities

    fun Direction.block() = when (this) {
        Direction.NORTH_WEST -> CollisionFlag.NORTH_AND_WEST
        Direction.NORTH_EAST -> CollisionFlag.NORTH_AND_EAST
        Direction.SOUTH_EAST -> CollisionFlag.SOUTH_AND_EAST
        Direction.SOUTH_WEST -> CollisionFlag.SOUTH_AND_WEST
        else -> flag()
    } shl type.shift or CollisionFlag.BLOCKED or extra

    fun Direction.clear() = when (this) {
        Direction.NORTH_WEST -> CollisionFlag.SOUTH_AND_EAST
        Direction.NORTH -> CollisionFlag.NOT_NORTH
        Direction.NORTH_EAST -> CollisionFlag.LAND_CLEAR_NORTH_EAST
        Direction.EAST -> CollisionFlag.SOUTH_AND_WEST
        Direction.SOUTH_EAST -> CollisionFlag.NOT_EAST
        Direction.SOUTH -> CollisionFlag.NORTH_AND_WEST
        Direction.SOUTH_WEST -> CollisionFlag.NOT_SOUTH
        Direction.WEST -> CollisionFlag.NORTH_AND_EAST
        Direction.NONE -> 0
    } shl type.shift or CollisionFlag.BLOCKED

}