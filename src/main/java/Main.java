import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Source source = Source.newBuilder("js",
            Objects.requireNonNull(Main.class.getClassLoader().getResource("./guestcode.js"))
        ).buildLiteral();

        try (Engine engine = Engine.newBuilder()
                .option("dap", "4711")
                .option("dap.Suspend", "true")
                .build()) {
            try (Context context = Context.newBuilder().engine(engine).build()) {
                Value result = context.eval(source);
                System.out.println(result);
            }
        }
    }
}
