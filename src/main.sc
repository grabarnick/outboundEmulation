require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Начнём.
    
    state: Outbound
        event!: eventName
        a: Исходящее сообщение
        
    state: Pushback
        q: link
        script:
            var pushback = $pushgate.createPushback(
                $request.channelType,
                $request.botId,
                $request.channelUserId,
                "eventName",
                {}
            );
            $session.pushlink = pushback.link
            # log("Link: " + pushback.link)
        a: Link: {{$session.pushlink}}



    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

