

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ryan on 11/23/14.
 */
public class ExtensionSpoofer implements Runnable {
    final String REVERSE_CHAR="\u202e";
File target;
    boolean backup;
    FileOutputStream fos;
    FileInputStream fis;
    String extension;
        public ExtensionSpoofer(File in, String extension, Boolean backup)
        {
            this.backup=backup;
            target=in;
            this.extension=extension;


        }
            public ExtensionSpoofer(String in, String extension, Boolean backup)
            {
                this.backup=backup;
                target=new File(in);
                this.extension=extension;


            }

    public void run() {
                    spoof(target, extension, backup);
            }

    public void spoof(File in, String extension, boolean backup) {
        try {
            if (backup) {
                FileInputStream fis = new FileInputStream(in);
                FileOutputStream fos = new FileOutputStream(new File(in.getAbsolutePath().replace(in.getName().substring(in.getName().lastIndexOf("."), in.getName().length()), ".bak")));
                byte[] array = new byte[1024];

                while (fis.read(array) != -1) {
                    fos.write(array);
                }

                fos.close();
                fis.close();
            }

            String path = in.getAbsolutePath();
            String name = in.getName();
/*
              ne is a file in the same directory, with the same first name, with different extension

 */
                              //*same directory------------------------*-* same first name-----------------------*-* reversed extension and name
            File ne = new File(path.substring(0, path.lastIndexOf(name)) + name.substring(0, name.lastIndexOf(".")) + REVERSE_CHAR + reverse(extension) + name.substring(name.lastIndexOf(".")));
            in.renameTo(ne);
        } catch (IOException e) {
            String[] error=new String[]{"IOException in: "+getClass().getName(), "Error in spoofing file extension"};
            new Thread(new MyException(e, error)).start();
        System.err.println("Debug, exception");

        }
    }

    public static String reverse(String str) {
        return new StringBuffer(str).reverse().toString();
    }
}
