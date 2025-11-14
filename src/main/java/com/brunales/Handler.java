package com.brunales;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;

public class Handler implements RequestHandler<S3Event, Void> {

    @Override
    public Void handleRequest(S3Event event, Context context) {

        context.getLogger().log(">>> Lambda triggered by S3 upload <<<\n");

        // Cogemos el primer registro del evento (un upload)
        S3EventNotification.S3EventNotificationRecord record = event.getRecords().get(0);

        String bucket = record.getS3().getBucket().getName();
        String key    = record.getS3().getObject().getKey();
        Long size     = record.getS3().getObject().getSizeAsLong();

        context.getLogger().log("Bucket: " + bucket + "\n");
        context.getLogger().log("Key: " + key + "\n");
        context.getLogger().log("Size: " + size + " bytes\n");

        // Para S3 normalmente no devolvemos nada
        return null;
    }
}
