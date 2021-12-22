package animals.serialization;

import animals.BinaryTree;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JacksonDeserializer extends JacksonBase {
    public static BinaryTree deserialize(FileFormat format) {
        ObjectMapper mapper = defineMapper(format);
        File file = defineFile(format);

        try {
            return mapper.readValue(file, BinaryTree.class);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
