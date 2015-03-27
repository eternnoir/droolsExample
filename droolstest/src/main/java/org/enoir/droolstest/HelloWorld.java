package org.enoir.droolstest;

import org.drools.core.event.DebugAgendaEventListener;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.Collection;


/**
 * Created by frank on 2015/3/26.
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello! World!");
        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();


// this will parse and compile in one step
        kbuilder.add(ResourceFactory.newClassPathResource("drools/rule.dri"), ResourceType.DRL);


// Check the builder for errors

        if (kbuilder.hasErrors()) {
            System.out.println(kbuilder.getErrors().toString());
            throw new RuntimeException("Unable to compile \"HelloWorld.drl\".");
        }


// get the compiled packages (which are serializable)
        final Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();


// add the packages to a knowledgebase (deploy the knowledge packages).
        final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

        kbase.addKnowledgePackages(pkgs);


        final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

        ksession.addEventListener( new DebugAgendaEventListener() );

        final Message message = new Message();

        message.setMessage("Hello World");

        message.setStatus(Message.HELLO);

        ksession.insert(message);

        ksession.fireAllRules();

        ksession.dispose();


    }
}

