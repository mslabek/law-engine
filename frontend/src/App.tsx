import React from "react";
import { Theme, ThemeProvider } from "@emotion/react";
import styled from "@emotion/styled";

const theme: Theme = {
    colors: {
        primary: "#5a189a",
        secondary: "#ff8500",
        background: "#e7dbe9",
    },
};

const AppContainer = styled.div<{ theme?: Theme }>(({ theme }) => ({
    backgroundColor: theme.colors.background,
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    height: "100vh",
}));

const App: React.FC = () => {
    return (
        <ThemeProvider theme={theme}>
            <AppContainer></AppContainer>
        </ThemeProvider>
    );
};

export default App;
