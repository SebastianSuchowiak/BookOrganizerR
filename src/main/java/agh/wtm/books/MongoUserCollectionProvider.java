package agh.wtm.books;

import agh.wtm.books.model.User;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.print.Doc;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoUserCollectionProvider {

    public static final String CONNECTION_STRING = "mongodb://localhost:27017";
    public static final String DATABASE = "bookOrganizer";
    public static final String COLLECTION = "users";

    private static MongoClient mongoClient;

    private MongoUserCollectionProvider() {

    }

    public static MongoCollection<User> getUsersCollection() {
        if (mongoClient == null) {
            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            MongoClientSettings settings = MongoClientSettings.builder()
                    .codecRegistry(pojoCodecRegistry)
                    .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                    .build();
            mongoClient = MongoClients.create(settings);
        }
        MongoDatabase database = mongoClient.getDatabase(DATABASE);
        MongoCollection<User> collection = database.getCollection(COLLECTION, User.class);

        return collection;
    }

}
