package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Game;

public class AILow extends AI {
	private int currentDirect = random.nextInt(4);
	private int countDown = Game.TILES_SIZE;
	private int count = Game.TILES_SIZE*10;
	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
		if(!canMove) {
			currentDirect = random.nextInt(4);
			canMove = true;
		}
		return currentDirect;
	}
}
