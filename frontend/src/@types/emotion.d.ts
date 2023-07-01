import "@emotion/react";

declare module "@emotion/react" {
    export interface Theme {
        colors: {
            primary: string;
            background: string;
            secondary100: string;
            secondary200: string;
            secondary300: string;
            secondary400: string;
            secondary500: string;
            secondary600: string;
            secondary700: string;
            secondary800: string;
            secondary900: string;
        };
    }
}
