package db.migration;

import com.opencsv.CSVReaderHeaderAware;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.stream.Collectors;

public class V2__insert_templates extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {

        Connection connection = context.getConnection();

        InputStream inputStream = new ClassPathResource("data/templates.csv").getInputStream();

//        try ( BufferedReader reader = new BufferedReader(
//                new InputStreamReader(inputStream)) ) {
//            String employees = reader.lines()
//                    .collect(Collectors.joining("\n"));
//
//            System.out.println(employees);
//        }

        CSVReaderHeaderAware strings = new CSVReaderHeaderAware(new InputStreamReader(inputStream, StandardCharsets.UTF_8));


        String [] nextLine;
        while((nextLine = strings.readNext()) != null) {
            try (
                    PreparedStatement pstm = connection.prepareStatement(
                            "INSERT INTO beleidigung(beleidigungs_template, antwort_template) VALUES ('%s', '%s')".formatted(nextLine[0], nextLine[1])
                    )
            ) {
                pstm.execute();
            }
        }
    }
}
