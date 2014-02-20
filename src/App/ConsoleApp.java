package App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import Game.Game;
import UI.iLogging;

public class ConsoleApp implements iLogging {
	private BufferedReader br;
	private PrintStream ps;
	
	public ConsoleApp(){
		br = new BufferedReader(new InputStreamReader(System.in));
		ps = System.out;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConsoleApp app = new ConsoleApp();
		new Game(app);

	}

	public String askInput(String out){
        ps.format(out);
        try{
            return br.readLine();
        }catch(IOException e)
        {
            return null;
        }
    }
	
	@Override
	public void write(String text) {
		System.out.println(text);
		
	}

}
