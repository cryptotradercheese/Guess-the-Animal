package animals.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;

abstract class JacksonBase {
    private static String fileNameWithoutExtension = "animals";

    static ObjectMapper defineMapper(FileFormat format) {
        ObjectMapper mapper;
        if (format == FileFormat.JSON) {
            mapper = new JsonMapper();
        } else if (format == FileFormat.XML) {
            mapper = new XmlMapper();
        } else if (format == FileFormat.YAML) {
            mapper = new YAMLMapper();
        } else {
            throw new IllegalArgumentException("Illegal format");
        }

        return mapper;
    }

    static File defineFile(FileFormat format) {
        return new File(
                fileNameWithoutExtension + "." +
                        format.toString().toLowerCase()
        );

    }

    public static boolean dbExists(FileFormat format) {
        return defineFile(format).exists();
    }
}
