package app.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author Anton German
 * @since 10 June 2016
 */
public class PrintSummaryTasklet implements Tasklet {

    @Autowired
    private DataSource dataSource;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext context) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        int booksCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOK", Integer.class);
        int authorsCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM AUTHOR", Integer.class);

        System.out.println("Summary:");
        System.out.println("-------------------------");
        System.out.println("Books loaded: " + booksCount);
        System.out.println("Authors loaded: " + authorsCount);
        System.out.println("-------------------------");

        return RepeatStatus.FINISHED;
    }
}
