import React from "react";
import { Theme, ThemeProvider } from "@emotion/react";
import styled from "@emotion/styled";
import { CssBaseline } from "@mui/material";
import Graph from "./components/Graph";

const theme: Theme = {
    colors: {
        primary: "#343a40",
        background: "#ffffff",
        secondary100: "#ffe8cc",
        secondary200: "#ffd8a8",
        secondary300: "#ffc078",
        secondary400: "#ffa94d",
        secondary500: "#ff922b",
        secondary600: "#fd7e14",
        secondary700: "#f76707",
        secondary800: "#e8590c",
        secondary900: "#d9480f",
    },
};

const AppContainer = styled.div<{ theme?: Theme }>(({ theme }) => ({
    backgroundColor: theme.colors.background,
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    height: "100vh",
}));

function App() {
    return (
        <CssBaseline>
            <ThemeProvider theme={theme}>
                <AppContainer>
                    <Graph></Graph>
                </AppContainer>
            </ThemeProvider>
        </CssBaseline>
    );
}

export default App;
