package com.fox2code.foxloader.installer;

import com.fox2code.foxloader.launcher.ServerMain;
import com.fox2code.foxloader.launcher.utils.SourceUtil;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;

public class Main {
    // We need -XX:+IgnoreUnrecognizedVMOptions cause some of the optimization arg we us are not available on some JVMs
    static final String optJvmArgs = "-XX:+IgnoreUnrecognizedVMOptions -XX:+UnlockExperimentalVMOptions " +
            "-XX:+UseFastAccessorMethods -XX:+AggressiveOpts -XX:+UseCompressedOops -XX:+UseBiasedLocking " +
            "-XX:+OptimizeStringConcat -XX:-UseGCOverheadLimit -XX:+UseLargePages -XX:+UseStringCache " +
            "-XX:+UseCompressedStrings -XX:+UseNUMA -XX:+UseCodeCacheFlushing -d64 -Dfile.encoding=UTF-8";
    static final String optJvmArgsWithMem = optJvmArgs + " -Xmn512M -Xms512M -Xmx2G";
    static final File currentInstallerFile = SourceUtil.getSourceFile(Main.class);
    public static void main(String[] args) throws ReflectiveOperationException, MalformedURLException {
        if (args.length == 0 && GraphicsEnvironment.isHeadless()) {
            ServerMain.main(args);
            return;
        }

        int move = 0;
        System.arraycopy(args, move, args, 0, args.length);
        ServerMain.main(Arrays.copyOf(args, args.length));
    }

    public static boolean isPojavLauncherHome(String userHome) {
        int index;
        return (userHome.startsWith("/storage/emulated/") && (index = userHome.indexOf('/', 18)) != -1 &&
                userHome.substring(index).startsWith("/Android/data/") || userHome.startsWith("/sdcard/Android/data/"));
    }
}
