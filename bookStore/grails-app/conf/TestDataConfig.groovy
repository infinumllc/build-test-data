import embedded.Embedded

testDataConfig {
    sampleData {
        // Hotel class is in "config" package so we use a string in the builder
        'config.Hotel' {
            // returns "Motel 6" for the name property whenever a Hotel is constructed
            // and a name is not already given
            name = "Motel 6"

            // returns a unique fax number at each request
            def i = 6125551111
            faxNumber = {-> "${i++}" } // creates "6125551111", "6125551112", .... 
        }
        'config.Article' {
            def i = 1
            name = {-> "Article ${i++}" }
        }

        // work around for embedded objects in src/groovy
        // grails does not create Artefacts that we can query for constraints for things outside of grails-app/domain
        'embedded.Embedding' {
            inner = new Embedded(someValue: "value")
        }
    }
}


// if you'd like to disable the build-test-data plugin in an environment, just set
// the "enabled" property to false

environments {
    production {
        testDataConfig {
            enabled = false
        }
    }
}