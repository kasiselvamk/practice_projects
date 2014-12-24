package emailClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

public class IfSh {
	public static String getDIP() throws ExecuteException, IOException, InterruptedException {
		String t ="X";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String line = "/home/pi/Desktop/if.sh";
		CommandLine cmdLine = CommandLine.parse(line);
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		Executor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);
		executor.setExitValue(1);
		ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
		executor.setWatchdog(watchdog);
		executor.execute(cmdLine,resultHandler);
		resultHandler.waitFor();
		if(resultHandler.hasResult()){
			System.out.println(  "\nJAVA:"+ resultHandler.hasResult()+":" + outputStream.toString());	
			t = outputStream.toString();
		}
		return t;
	}

}
