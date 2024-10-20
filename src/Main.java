import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(args[0]));

            int programIndex = 0;
            byte[] program = bufferedInputStream.readAllBytes();

            int memoryIndex = 0;
            byte[] memory = new byte[3000];
            while (programIndex < program.length) {
                switch (program[programIndex]) {
                    case '>':
                        memoryIndex++;
                        break;
                    case '<':
                        memoryIndex--;
                        break;
                    case '+':
                        memory[memoryIndex]++;
                        break;
                    case '-':
                        memory[memoryIndex]--;
                        break;
                    case '.':
                        System.out.write(memory[memoryIndex]);
                        break;
                    case ',':
                        System.out.flush();
                        memory[memoryIndex] = (byte) System.in.read();
                        break;
                    case '[':
                        if (memory[memoryIndex] == 0) {
                            int i = 1;
                            while (i > 0)   {
                                programIndex++;
                                switch (program[programIndex])  {case '[' -> i++; case ']' -> i--;}
                            }
                        }
                        break;
                    case ']':
                        if (memory[memoryIndex] != 0) {
                            int i = 1;
                            while (i > 0)   {
                                programIndex--;
                                switch (program[programIndex])  {case ']' -> i++; case '[' -> i--;}
                            }
                        }
                        break;
                }
                programIndex++;
            }
            System.out.flush();
    }
}