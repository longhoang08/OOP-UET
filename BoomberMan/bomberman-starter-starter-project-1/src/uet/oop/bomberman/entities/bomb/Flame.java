package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.level.Coordinates;

public class Flame extends Entity {

	protected Board _board;
	protected int _direction;
	private int _radius;
	protected int xOrigin, yOrigin;
	protected FlameSegment[] _flameSegments = new FlameSegment[0];

	/**
	 *
	 * @param x hoành độ bắt đầu của Flame
	 * @param y tung độ bắt đầu của Flame
	 * @param direction là hướng của Flame
	 * @param radius độ dài cực đại của Flame
	 */
	public Flame(int x, int y, int direction, int radius, Board board) {
		xOrigin = x;
		yOrigin = y;
		_x = x;
		_y = y;
		_direction = direction;
		_radius = radius;
		_board = board;
		createFlameSegments();
	}

	/**
	 * Tạo các FlameSegment, mỗi segment ứng một đơn vị độ dài
	 */
	private void createFlameSegments() {
		/**
		 * tính toán độ dài Flame, tương ứng với số lượng segment
		 */
		/**
		 * biến last dùng để đánh dấu cho segment cuối cùng
		 */
		boolean last = false;
		// TODO: tạo các segment dưới đây
		_flameSegments = new FlameSegment[calculatePermitedDistance()];
		for(int i = 0; i < calculatePermitedDistance(); i++) {
			if(i==calculatePermitedDistance()-1)
				last = true;
			if(_direction==0)
				_flameSegments[i] = new FlameSegment(xOrigin, yOrigin - i, _direction, last);
			if(_direction==1)
				_flameSegments[i] = new FlameSegment(xOrigin + i, yOrigin, _direction, last);
			if(_direction==2)
				_flameSegments[i] = new FlameSegment(xOrigin, yOrigin + i, _direction, last);
			if(_direction==3)
				_flameSegments[i] = new FlameSegment(xOrigin - i, yOrigin, _direction, last);
			_flameSegments[i].collide(_board.getCharacterAtExcluding((int)_x, (int)_y, null));
		}


	}

	/**
	 * Tính toán độ dài của Flame, nếu gặp vật cản là Brick/Wall, độ dài sẽ bị cắt ngắn
	 * @return
	 */
	private int calculatePermitedDistance() {
		// TODO: thực hiện tính toán độ dài của Flame
		return _radius;
	}
	
	public FlameSegment flameSegmentAt(int x, int y) {
		for (int i = 0; i < _flameSegments.length; i++) {
			if(_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
				return _flameSegments[i];
		}
		return null;
	}

	@Override
	public void update() {
		for(int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].update();
		}
	}
	
	@Override
	public void render(Screen screen) {
		for (int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].render(screen);
		}
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý va chạm với Bomber, Enemy. Chú ý đối tượng này có vị trí chính là vị trí của Bomb đã nổ
			if(e instanceof Character || e instanceof Enemy) {
				((Character) e).kill();
			}
		return false;
	}
}
