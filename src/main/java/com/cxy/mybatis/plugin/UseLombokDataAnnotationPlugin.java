package com.cxy.mybatis.plugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * 使用Lombok @Data注解代替Getter、Setter方法
 * @author cxy
 * @since 2019/4/10
 */
public class UseLombokDataAnnotationPlugin extends PluginAdapter {

    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        //该代码表示在生成class的时候，向topLevelClass添加一个@Setter和@Getter注解
//        topLevelClass.addAnnotation("@Getter@Setter");
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addAnnotation("@Data");
        return super.modelBaseRecordClassGenerated(topLevelClass,
                introspectedTable);
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }

    //该方法在生成每一个属性的setter方法时候调用，如果我们不想生成setter，直接返回false即可；
    @Override
    public boolean modelSetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }
}
