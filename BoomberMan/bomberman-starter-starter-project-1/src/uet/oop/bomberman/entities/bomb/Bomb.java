package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

public class Bomb extends AnimatedEntitiy {

	protected double _timeToExplode = 120; //2 seconds
	public int _timeAfter = 30;
	
	protected Board _board;
	protected Flame[] _flames;
	protected boolean _exploded = false;
	protected boolean _allowedToPassThru = true;
	
	public Bomb(int x, int y, Board board) {
		_x = x;
		_y = y;
		_board = board;
		_sprite = Sprite.bomb;
	}
	
	@Override
	public void update() {
		if(_timeToExplode > 0) 
			_timeToExplode--;
		else {
			if(!_exploded) 
				explode();
			else
				updateFlames();
			
			if(_timeAfter > 0) 
				_timeAfter--;
			else
				remove();
		}
			
		animate();
	}
	
	@Override
	public void render(Screen screen) {
		if(_exploded) {
			_sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, _animate, 60);
			renderFlames(screen);
		} else
			_sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
		
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntityWithBelowSprite(xt, yt , this, Sprite.grass);
	}
	
	public void renderFlames(Screen screen) {
		for (int i = 0; i < _flames.length; i++) {
			_flames[i].render(screen);
		}
	}
	
	public void updateFlames() {
		for (int i = 0; i < _flames.length; i++) {
			_flames[i].update();
		}
	}

    /**
     * Xử lý Bomb nổ
     */
	protected void explode() {
		// TODO: tạo các Flame
		_flames = new Flame[4];
		_flames[0] = new Flame((int)_x, (int)_y - 1, 0, Game.getBombRadius(), _board);
		_flames[1] = new Flame((int)_x + 1, (int)_y, 1, Game.getBombRadius(), _board);
		_flames[2] = new Flame((int)_x, (int)_y + 1, 2, Game.getBombRadius(), _board);
		_flames[3] = new Flame((int)_x - 1, (int)_y , 3, Game.getBombRadius(), _board);
		_exploded = true;
		// TODO: xử lý khi Character đứng tại vị trí Bomb
		Character ch = _board.getCharacterAtExcluding((int)_x, (int)_y, null);
		if(ch!=null) {
			ch.kill();
		}
	}
	
	public FlameSegment flameAt(int x, int y) {
		if(!_exploded) return null;
		
		for (int i = 0; i < _flames.length; i++) {
			if(_flames[i] == null) return null;
			FlameSegment e = _flames[i].flameSegmentAt(x, y);
			if(e != null) return e;
		}
		
		return null;
	}

	@Override
	public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi ra sau khi vừa đặt bom (_allowedToPassThru)
		if(e instanceof Bomber) {
			double loLy = e.getY() - 1;
			double loRy = e.getY() - 1;
			double upLy = e.getY() + 1 - Game.TILES_SIZE;
			double upRy = e.getY() + 1 - Game.TILES_SIZE;
			double upLx = e.getX() + 1;
			double loLx = e.getX() + 1;
			double upRx = e.getX() - 1 + Game.TILES_SIZE * 3 / 4;
			double loRx = e.getX() - 1 + Game.TILES_SIZE * 3 / 4;
			int tile_UpLx = Coordinates.pixelToTile(upLx);
			int tile_UpLy = Coordinates.pixelToTile(upLy);
			int tile_UpRx = Coordinates.pixelToTile(upRx);
			int tile_UpRy = Coordinates.pixelToTile(upRy);
			int tile_LoLx = Coordinates.pixelToTile(loLx);
			int tile_LoLy = Coordinates.pixelToTile(loLy);
			int tile_LoRx = Coordinates.pixelToTile(loRx);
			int tile_LoRy = Coordinates.pixelToTile(loRy);
			if ((tile_LoLx == _x && tile_LoLy == _y)
					|| (tile_LoRx == _x && tile_LoRy == _y) || (tile_UpLx == _x && tile_UpLy == _y)
					|| (tile_UpRx == _x && tile_UpRy == _y)) {
					return false;
			}
		}
        // TODO: xử lý va chạm với Flame của Bomb khác
		return true;
	}
}
