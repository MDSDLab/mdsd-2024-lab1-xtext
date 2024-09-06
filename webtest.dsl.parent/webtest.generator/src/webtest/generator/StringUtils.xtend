package webtest.generator;

class StringUtils {
	def static String toPascalCase(String text) {
		if (text === null || text.length() == 0) return text;
		return Character.toUpperCase(text.charAt(0))+text.substring(1);
	}
	
	def static String toCamelCase(String text) {
		if (text === null || text.length() == 0) return text;
		return Character.toLowerCase(text.charAt(0))+text.substring(1);
	}
	
	def static String encodeString(String text) {
		return encodeString(text, false);
	}
	
	def static String encodeString(String text, boolean withoutQuotes) {
		var builder = new StringBuilder();
        if (!withoutQuotes) builder.append('"');
        for (char ch: text.toCharArray())
        {
            if (ch == 34 || ch == 92)
            {
                builder.append("\\");
                builder.append(ch);
            }
            else if (ch == 0)
            {
                builder.append("\\0");
            }
            else if (ch == 7)
            {
                builder.append("\\a");
            }
            else if (ch == 8)
            {
                builder.append("\\b");
            }
            else if (ch == 9)
            {
                builder.append("\\t");
            }
            else if (ch == 10)
            {
                builder.append("\\n");
            }
            else if (ch == 11)
            {
                builder.append("\\v");
            }
            else if (ch == 12)
            {
                builder.append("\\f");
            }
            else if (ch == 13)
            {
                builder.append("\\r");
            }
            else 
            {
                builder.append(ch);
            }
        }
        if (!withoutQuotes) builder.append('"');
		return builder.toString();
	}
}
