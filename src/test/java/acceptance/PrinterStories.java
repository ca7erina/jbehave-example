package acceptance;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.util.List;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import steps.PrinterSteps;
import steps.PrinterWithColorSteps;



public class PrinterStories extends JUnitStories  {
  private final CrossReference xref = new CrossReference();

  public PrinterStories() {
    configuredEmbedder()
        .embedderControls()
        .doGenerateViewAfterStories(true)
        .doIgnoreFailureInStories(false)
        .doIgnoreFailureInView(true)
        .doVerboseFailures(true)
        .useThreads(2);
  }

  @Override
  public Configuration configuration() {
    Class<? extends Embeddable> embeddableClass = this.getClass();
    Properties viewResources = new Properties();
    viewResources.put("decorateNonHtml", "true");
    viewResources.put("reports", "ftl/jbehave-reports.ftl");

    return new MostUsefulConfiguration()
        .useStoryLoader(new LoadFromClasspath(embeddableClass))
        .useStoryReporterBuilder(
            new StoryReporterBuilder()
                .withCodeLocation(codeLocationFromClass(embeddableClass))
                .withDefaultFormats()
                .withViewResources(viewResources)
                .withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML)
                .withFailureTrace(true)
                .withFailureTraceCompression(true)
                .withCrossReference(xref));
  }

  @Override
  public InjectableStepsFactory stepsFactory() {
    return new InstanceStepsFactory(
        configuration(), new PrinterSteps(), new PrinterWithColorSteps());
  }

  @Override
  protected List<String> storyPaths() {
    String filter = System.getProperty("story.filter", "**/*.story");
    return new StoryFinder()
        .findPaths(
            codeLocationFromClass(this.getClass()),
            filter,
            "**/failing/*.story,**/given/*.story,**/pending/*.story");
  }
}
