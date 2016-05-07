package net.duck8823;


import com.google.common.io.Resources;
import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourceSubjectFactory;
import org.junit.Test;

/**
 * {@link GenerateNamesProcessor}のテスト
 * Created by maeda on 2016/01/09.
 */
public class ProcessorTest {

	@Test
	public void 正しく名前クラスが生成される() {
		Truth.assert_().about(JavaSourceSubjectFactory.javaSource())
				.that(JavaFileObjects.forResource(Resources.getResource("Hoge.java")))
				.processedWith(new GenerateNamesProcessor())
				.compilesWithoutError()
				.and()
				.generatesSources(JavaFileObjects.forResource(Resources.getResource("HogeNames.java")));
	}

	@Test
	public void スーパークラスのフィールドを取得しない() {
		Truth.assert_().about(JavaSourceSubjectFactory.javaSource())
				.that(JavaFileObjects.forResource(Resources.getResource("HogeWithoutSuperclass.java")))
				.processedWith(new GenerateNamesProcessor())
				.compilesWithoutError()
				.and()
				.generatesSources(JavaFileObjects.forResource(Resources.getResource("HogeWithoutSuperclassNames.java")));
	}
}
