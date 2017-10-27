package acceptance;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStoryMaps;
import org.jbehave.core.reporters.StoryReporterBuilder;


public class StoryMap extends JUnitStoryMaps {
  public StoryMap() {
    configuredEmbedder().useMetaFilters(metaFilters());
  }

  @Override
  public Configuration configuration() {
    return new MostUsefulConfiguration()
      .useStoryReporterBuilder(new StoryReporterBuilder()
        .withCodeLocation(codeLocationFromClass(this.getClass())));
  }

  @Override
  protected List<String> metaFilters() {
    return asList("+author *", "theme *","-skip");
  }

  @Override
  protected List<String> storyPaths() {
    return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story", "");
  }
}
