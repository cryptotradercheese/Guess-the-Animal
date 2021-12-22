package animals.serialization;

import animals.BinaryTree;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JacksonSerializer extends JacksonBase {
    public static void serialize(FileFormat format, BinaryTree serializable) {
        ObjectMapper mapper = defineMapper(format);
        File file = defineFile(format);

        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, serializable);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
