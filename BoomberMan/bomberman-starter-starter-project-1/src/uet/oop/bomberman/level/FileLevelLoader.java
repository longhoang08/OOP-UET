package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		Class c = null;
		try {
			c = Class.forName("uet.oop.bomberman.level.FileLevelLoader");
			InputStream stream = c.getResourceAsStream("/levels/Level1.txt");
			Reader r = new InputStreamReader(stream, "UTF-8");
			BufferedReader br = new BufferedReader(r);

			String line;
			line = br.readLine();
			String[] sizes = line.split("\\s");
			_level = Integer.parseInt(sizes[0]);
			_height = Integer.parseInt(sizes[1]);
			_width = Integer.parseInt(sizes[2]);
			_map = new char[_width][_height];
			int rowNum = 0;
			while((line = br.readLine())!=null) {
				for(int i = 0; i < line.length(); i++) {
					_map[i][rowNum]=line.charAt(i);
				}
				rowNum++;
			}
			br.close();
		} catch (IOException e) {
			System.err.println("Read From File Failed.");
		} catch (ClassNotFoundException e) {
			System.err.println("Get Resources Failed.");
		}

	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
		// thêm Wall
//		for (int x = 0; x < _width; x++) {
//			for (int y = 0; y < _height; y++) {
//				int pos = x + y * _width;
//				Sprite sprite = y == 0 || x == 0 || x == _width-1 || y == _height-1 ? Sprite.wall : Sprite.grass;
//				_board.addEntity(pos, new Grass(x, y, sprite));
//			}
//		}
		for(int x = 0; x < _width ; x++) {
			for(int y = 0; y < _height; y++) {
				int pos = x + y * _width;
                //TODO: Add Bomber
				if(_map[x][y]== 'p') {
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
					_board.addCharacter( new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
				}
				//TODO: Add Ballons
				else if(_map[x][y]=='1') {
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
					_board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
				}
				//TODO: Add Oneals
				else if(_map[x][y]=='2') {
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
					_board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));

				}//TODO: Add Bricks
				else if(_map[x][y]=='*') {
					_board.addEntity(pos,
							new LayeredEntity(x, y,
									new Grass(x, y, Sprite.grass),
									new Brick(x, y, Sprite.brick)
							)
					);
				}
				//TODO: Add Bomb Items
				else if(_map[x][y]=='b') {
					_board.addEntity(pos,
							new LayeredEntity(x, y,
									new Grass(x, y, Sprite.grass),
									new SpeedItem(x, y, Sprite.powerup_bombs),
									new Brick(x, y, Sprite.brick)
							)
					);
				}
				//TODO: Add Flame Items
				else if(_map[x][y]=='f') {
					_board.addEntity(pos,
							new LayeredEntity(x, y,
									new Grass(x, y, Sprite.grass),
									new SpeedItem(x, y, Sprite.powerup_flames),
									new Brick(x, y, Sprite.brick)
							)
					);
				}
				//TODO: Add Speed Items
				else if(_map[x][y]=='s') {
					_board.addEntity(pos,
							new LayeredEntity(x, y,
									new Grass(x, y, Sprite.grass),
									new SpeedItem(x, y, Sprite.powerup_speed),
									new Brick(x, y, Sprite.brick)
							)
					);
				}
				//TODO: Add Walls
				else if(_map[x][y]=='#')
					_board.addEntity(pos, new Wall(x, y, Sprite.wall));
				//TODO: Add Portals
				else if(_map[x][y]=='x')
					_board.addEntity(pos,
							new LayeredEntity(x, y,
									new Grass(x ,y, Sprite.grass),
									new SpeedItem(x, y, Sprite.portal),
									new Brick(x, y, Sprite.brick)
							)
					);
				//TODO: Add Grasses
				else
					_board.addEntity(pos, new Grass(x, y, Sprite.grass));
			}
		}
	}
}
