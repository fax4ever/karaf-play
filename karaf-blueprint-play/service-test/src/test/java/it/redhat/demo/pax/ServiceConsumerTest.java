package it.redhat.demo.pax;

import java.io.File;
import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.redhat.demo.api.CiaoService;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.ConfigurationManager;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.options.MavenUrlReference;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;

@RunWith(PaxExam.class)
public class ServiceConsumerTest {

	@Inject
	private CiaoService service;

	@Configuration
	public Option[] config() {
		MavenArtifactUrlReference karafUrl = maven()
				.groupId("org.apache.karaf")
				.artifactId("apache-karaf")
				.version(karafVersion())
				.type("zip");

		MavenUrlReference karafStandardRepo = maven()
				.groupId("org.apache.karaf.features")
				.artifactId("standard")
				.version(karafVersion())
				.classifier("features")
				.type("xml");

		return new Option[] {
				// KarafDistributionOption.debugConfiguration("5005", true),
				karafDistributionConfiguration()
						.frameworkUrl(karafUrl)
						.unpackDirectory(new File( "target", "exam"))
						.useDeployFolder(false),
				keepRuntimeFolder(),
				configureConsole().ignoreLocalConsole(),
				features(karafStandardRepo , "scr"),
				mavenBundle()
						.groupId("it.redhat.demo")
						.artifactId("blueprint-service-producer")
						.version( "1.0-SNAPSHOT" ).start(),
				//logLevel( LogLevelOption.LogLevel.TRACE ),
		};
	}

	public static String karafVersion() {
		ConfigurationManager cm = new ConfigurationManager();
		String karafVersion = cm.getProperty("apache.karaf.version", "4.2.0");
		return karafVersion;
	}

	@Test
	public void testCiao() {
		String ciao = service.ciao( "Fabio" );
		Assert.assertNotNull( ciao );
	}
}
