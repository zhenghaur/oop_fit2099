package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.items.FireAttack;
import game.items.FireFlower;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.mobs.Bowser;
import game.mobs.Koopa;
import game.npcs.PrincessPeach;
import game.npcs.Toad;
import game.terrains.*;
import game.weapons.Wrench;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(), new Lava(), new HealthFountain(), new PowerFountain());

		List<String> map1 = Arrays.asList(
			"..........................................##..........+.........................",
			"............+............+..................#...................................",
			"............................................#...................................",
			".............................................##......................+..........",
			"...............................................#................................",
			"................................................#...............................",
			".................+.........................H......#.............................",
			".................................................##.............................",
			".................................###.......A....##..............................",
			".........+.......................#.#....+#____####.................+............",
			".................................###...+#_____###++.............................",
			".......................................+#______###..............................",
			"........................................+#_____###..............................",
			"........................+........................##.............+...............",
			"...................................................#............................",
			"....................................................#...........................",
			"...................+.................................#..........................",
			"......................................................#.........................",
			".......................................................##.......................");

		List<String> map2 = Arrays.asList(
				"...................LLLL...........................LL...",
				".......LLL.........LL.......................+....LLLL..",
				"....+..LL.....................LLL...........#...LLL....",
				".............................LL........................",
				".....LL.....+...............LL.................+....+..",
				"......LLL..............................................",
				".......LL.................................LLL.....#....",
				"..............LLL........................LL.......#....",
				"...............LLLLL...LL.....##+.........LL....#+.....",
				"......................LLLL.....##L##....#+#LL..#.......",
				".....+...LLLLLL...............##LL.........LL..........");

		GameMap gameMap1 = new GameMap(groundFactory, map1);
		GameMap gameMap2 = new GameMap(groundFactory, map2);
		world.addGameMap(gameMap1);
		world.addGameMap(gameMap2);

		WarpPipe warpPipeLava = new WarpPipe();
		WarpPipe warpPipe1 = new WarpPipe(warpPipeLava);
		WarpPipe warpPipe2 = new WarpPipe(warpPipeLava);
		WarpPipe warpPipe3 = new WarpPipe(warpPipeLava);
		WarpPipe warpPipe4 = new WarpPipe(warpPipeLava);

		gameMap1.at(42, 8).setGround(warpPipe1);
		gameMap1.at(70, 4).setGround(warpPipe2);
		gameMap1.at(5, 13).setGround(warpPipe3);
		gameMap1.at(41, 18).setGround(warpPipe4);
		gameMap2.at(0,0).setGround(warpPipeLava);

		Actor mario = new Player("Player", 'm', 1000);
		world.addPlayer(new Toad(), gameMap1.at(34, 9));
		world.addPlayer(mario, gameMap1.at(42, 10));

		gameMap1.at(42,10).addItem(new PowerStar());
		gameMap1.at(42,10).addItem(new SuperMushroom());

		gameMap2.at(15, 2).addActor(new Bowser(gameMap2.at(15, 2)));
		gameMap2.at(16, 2).addActor(new PrincessPeach());

//		mario.addItemToInventory(new Wrench());


		world.run();

	}
}
