// @ts-check
import { defineConfig } from "astro/config";
import starlight from "@astrojs/starlight";

import markdoc from "@astrojs/markdoc";

// https://astro.build/config
export default defineConfig({
  site: 'https://kingssack.github.io',
  base: '/Volt',
  integrations: [
    starlight({
      title: "Volt",
      logo: {
        src: "./src/assets/favicon.svg",
      },
      social: [
        {
          icon: "github",
          label: "GitHub",
          href: "https://github.com/KingsSack/Volt",
        },
      ],
      tableOfContents: {
        minHeadingLevel: 2,
        maxHeadingLevel: 5,
      },
      editLink: {
        baseUrl:
          "https://github.com/KingsSack/Volt/edit/main/docs/",
      },
      sidebar: [
        {
          label: "Getting Started",
          items: [{ autogenerate: { directory: "getting-started" } }],
        },
        {
          label: "Guides",
          items: [{ autogenerate: { directory: "guides" } }],
        },
        {
          label: "Concepts",
          items: [{ autogenerate: { directory: "concepts" } }],
        },
        {
          label: "Reference",
          items: [{ autogenerate: { directory: "reference" } }],
        },
        {
          label: "API Reference",
          link: "/api",
        },
      ],
      customCss: ["./src/styles/theme.css", "./src/styles/landing.css"],
    }),
    markdoc(),
  ],
});
